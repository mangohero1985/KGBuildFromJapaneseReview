package TextFeatureComputation.FeatureSelectionTools;

/**
 * This class gives the following popular metrics for feature selection.
 * <ul>
 *   <li>Mutual Information (MI)
 *   <li>Chi-square
 *   <li>Information Gain (IG)
 * </ul>
 *
 * In order to calculate the scores above, it needs to first count the number
 * of feature occurrences.  Specifically, it assumes the following confusion
 * matrix for feature selection.
 *
 *                           Feature occurrence
 *                           yes  no
 * Gold standard class  yes  tp  fn
 *                      no   fp  tn
 *
 * In this matrix, tp, fn, fp, and tn stand for the number of true positive,
 * false negative, false positive, and true negative, respectively.  The
 * variable n is the sum of tp, fn, fp, and tn.  For more information on
 * feature selection, see:
 *
 * Christopher D. Manning, Prabhakar Raghavan, and Hinrich Schtze. 2008.
 * Introduction to Information Retrieval. Cambridge University Press.
 *
 * George Forman, Isabelle Guyon, and Andr Elisseeff. 2003. An Extensive
 * Empirical Study of Feature Selection Metrics for Text Classification.
 * Journal of Machine Learning Research, 3:1289-1305.
 *
 * @author Jun Araki
 */
public class FeatureSelectionMetrics extends FeatureOccurrenceCounter {
 
  /** Mutual information score */
  private Double mi;
 
  /** Chi-square score */
  private Double chiSquare;
 
  /** Information gain score */
  private Double ig;
 
  /**
   * Constructor.
   */
  public FeatureSelectionMetrics() {
    super();
  }
 
  /**
   * Constructor taking respective counts.
   *
   * @param tp
   * @param fn
   * @param fp
   * @param tn
   */
  public FeatureSelectionMetrics(double tp, double fn, double fp, double tn) {
    super(tp, fn, fp, tn);
  }
 
  /**
   * Calculates and returns the mutual information score.
   *
   * @return the mutual information score
   */
  public Double getMI() {
    calculateMutualInformation();
    return mi;
  }
 
  /**
   * Calculates and returns the chi-square score.
   *
   * @return the chi-square score
   */
  public Double getChiSquare() {
    calculateChiSquare();
    return chiSquare;
  }
 
  /**
   * Calculates and returns the information gain score.
   *
   * @return the information gain score
   */
  public Double getIG() {
    calculateInformationGain();
    return ig;
  }
 
  /**
   * Calculates mutual information given the counts from tp to tn.  For more
   * information, see (Manning et al., 2008).
   */
  private void calculateMutualInformation() {
    if (tp == 0 || fn == 0 || fp == 0 || tn == 0) {
      // Boundary cases
      mi = null;
      return;
    }
 
    calculateSum();
    double gPos = getGoldStandardPositives();
    double gNeg = getGoldStandardNegatives();
    double fPos = getPredictedPositives();
    double fNeg = getPredictedNegatives();
 
    mi = (tp / n) * log2((n * tp) / (gPos * fPos))
       + (fp / n) * log2((n * fp) / (gNeg * fPos))
       + (fn / n) * log2((n * fn) / (gPos * fNeg))
       + (tn / n) * log2((n * tn) / (gNeg * fNeg));
  }
 
  /**
   * Calculates the chi-square score given the counts from tp to tn.  In
   * order to know statistical significance of the score, you can refer to
   * the following relationship between the p value and chi-square score
   * (Manning et al., 2008).
   *
   * p value   chi-square
   * 0.1       2.71
   * 0.05      3.84
   * 0.01      6.63
   * 0.005     7.88
   * 0.001     10.83
   */
  private void calculateChiSquare() {
    if (tp + fp == 0 || tp + fn == 0 || fn + tn == 0 || fp + tn == 0) {
      // Boundary cases.
      chiSquare = null;
      return;
    }
 
    calculateSum();
    // An arithmetically simpler way of computing chi-square
    chiSquare = (n * Math.pow((tp * tn - fn * fp), 2))
                / ((tp + fp) * (tp + fn) * (fn + tn) * (fp + tn));
  }
 
  /**
   * Calculates the information gain score given the counts from tp to tn.
   * For more information, see (Forman et al., 2003).
   */
  private void calculateInformationGain() {
    if (tp == 0 || fn == 0 || fp == 0 || tn == 0) {
      // Boundary cases
      ig = null;
      return;
    }
 
    calculateSum();
    double gPos = getGoldStandardPositives();
    double gNeg = getGoldStandardNegatives();
    double fPos = getPredictedPositives();
    double fNeg = getPredictedNegatives();
 
    // Information gain = (entropy when a feature is absent) - (entropy when a feature is present)
    ig = - (gPos / n) * log2 (gPos / n) - (gNeg / n) * log2 (gNeg / n)
         + (tp / n) * log2 (tp / fPos) + (fp / n) * log2 (fp / fPos)
         + (fn / n) * log2 (fn / fNeg) + (tn / n) * log2 (tn / fNeg);
  }
 
  private double log2(double value) {
    return (Math.log(value) / Math.log(2));
  }
 
  /**
   * A simple tester with a couple of examples.
   *
   * @param args
   */
//  public static void main(String[] args) {
//    FeatureSelectionMetrics fsm1 = new FeatureSelectionMetrics(49, 141, 27652, 774106);
//    Double mi1 = fsm1.getMI();
//    Double chiSquare1 = fsm1.getChiSquare();
//    Double ig1 = fsm1.getIG();
// 
//    FeatureSelectionMetrics fsm2 = new FeatureSelectionMetrics(0, 4, 0, 164);
//    Double mi2 = fsm2.getMI();
//    Double chiSquare2 = fsm2.getChiSquare();
//    Double ig2 = fsm2.getIG();
// 
//    System.out.println("mi1: " + mi1);  // Should be approximately 0.0001105
//    System.out.println("chiSquare1: " + chiSquare1);  // Should be approximately 284
//    System.out.println("ig1: " + ig1);
// 
//    // The scores below should be undefined (null) due to boundary cases.
//    System.out.println("mi2: " + mi2);
//    System.out.println("chiSquare2: " + chiSquare2);
//    System.out.println("ig2: " + ig2);
//  }
// 
}