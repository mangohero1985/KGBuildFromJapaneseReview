package FeatureSelection;

/**
 * This class counts the number of feature occurrences given a particular class.
 * Specifically, it assume the following confusion matrix for feature selection.
 *
 *                           Feature occurrence
 *                           yes  no
 * Gold standard class  yes  tp   fn
 *                      no   fp   tn
 *
 * In this matrix, tp, fn, fp, and tn stand for the number of true positive,
 * false negative, false positive, and true negative, respectively.  The
 * variable n is the sum of tp, fn, fp, and tn.
 *
 * @author Jun Araki
 */
public class FeatureOccurrenceCounter {
 
  protected double tp;
 
  protected double fn;
 
  protected double fp;
 
  protected double tn;
 
  protected double n;
 
  /**
   * Constructor.
   */
  public FeatureOccurrenceCounter() {
    tp = 0.0;
    fn = 0.0;
    fp = 0.0;
    tn = 0.0;
  }
 
  /**
   * Constructor with respective counts.
   *
   * @param tp
   * @param fn
   * @param fp
   * @param tn
   */
  public FeatureOccurrenceCounter(double tp, double fn, double fp, double tn) {
    this.tp = tp;
    this.fn = fn;
    this.fp = fp;
    this.tn = tn;
  }
 
  public void calculateSum() {
    n = tp + fn + fp + tn;
  }
 
  public void incrementTruePositive() {
    incrementTruePositive(1);
  }
 
  public void incrementTruePositive(double delta) {
    tp += delta;
  }
 
  public void incrementFalseNegative() {
    incrementFalseNegative(1);
  }
 
  public void incrementFalseNegative(double delta) {
    fn += delta;
  }
 
  public void incrementFalsePositive() {
    incrementFalsePositive(1);
  }
 
  public void incrementFalsePositive(double delta) {
    fp += delta;
  }
 
  public void incrementTrueNegative() {
    incrementTrueNegative(1);
  }
 
  public void incrementTrueNegative(double delta) {
    tn += delta;
  }
 
  public double getTruePositive() {
    return tp;
  }
 
  public void setTruePositive(double tp) {
    this.tp = tp;
  }
 
  public double getFalseNegative() {
    return fn;
  }
 
  public void setFalseNegative(double fn) {
    this.fn = fn;
  }
 
  public double getFalsePositive() {
    return fp;
  }
 
  public void setFalsePositive(double fp) {
    this.fp = fp;
  }
 
  public double getTrueNegative() {
    return tn;
  }
 
  public void setTrueNegative(double tn) {
    this.tn = tn;
  }
 
  public double getGoldStandardPositives() {
    return (tp + fn);
  }
 
  public double getGoldStandardNegatives() {
    return (fp + tn);
  }
 
  public double getPredictedPositives() {
    return (tp + fp);
  }
 
  public double getPredictedNegatives() {
    return (fn + tn);
  }
 
  public double getAll() {
    calculateSum();
    return n;
  }
 
}