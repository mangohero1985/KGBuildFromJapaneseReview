/**
 * 
 */
package SparqlQuery;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author mangohero1985
 * @create-time Aug 14, 2014 8:38:08 PM
 */
public class QueryFromDBpedia {

	// 检查返回结果是否为空
	public int IsEmptyJudgement(String Keys) {
		int value = 0;
		String sparqlQueryString = "prefix dcterms: <http://purl.org/dc/terms/> " + "prefix category-ja: <http://ja.dbpedia.org/resource/Category:>"
				+ "prefix dbpedia-ja:	<http://ja.dbpedia.org/resource/>" + "prefix dbpedia-owl:	<http://dbpedia.org/ontology/> " + "   SELECT ?o" + "   WHERE {  " + " ?Keys"
				+ " dbpedia-owl:wikiPageWikiLink ?o ." + " }";

		ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(sparqlQueryString) {
			{
				setNsPrefix("dbpedia-ja", "http://ja.dbpedia.org/resource/");
			}
		};
		parameterizedSparqlString.setIri("?Keys", parameterizedSparqlString.getNsPrefixURI("dbpedia-ja") + Keys);
		System.out.println(Keys);
		Query query = QueryFactory.create(parameterizedSparqlString.toString());
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();
		if (results.hasNext()) {

			// while (results.hasNext()) {
			// System.out.println(results.next().toString());
			// }
			value = 1;

		}
		// ResultSetFormatter.out(System.out, results, query);

		qexec.close();
		return value;

	}

	// 返回WikiLink的结果
	public String WikiLink(String Keys) {

		String WikiLinkes = "null";
		String sparqlQueryString = "prefix dcterms: <http://purl.org/dc/terms/> " + "prefix category-ja: <http://ja.dbpedia.org/resource/Category:>"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + "prefix dbpedia-ja:	<http://ja.dbpedia.org/resource/>"
				+ "prefix dbpedia-owl:	<http://dbpedia.org/ontology/> " + "   SELECT (group_concat(?label; separator=\",\") as ?Elements)" + "   WHERE {  " + " ?Keys"
				+ " dbpedia-owl:wikiPageWikiLink ?o ." + "?o rdfs:label ?label" + " }";

		ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(sparqlQueryString) {
			{
				setNsPrefix("dbpedia-ja", "http://ja.dbpedia.org/resource/");
			}
		};
		parameterizedSparqlString.setIri("?Keys", parameterizedSparqlString.getNsPrefixURI("dbpedia-ja") + Keys);
		System.out.println(Keys);
		Query query = QueryFactory.create(parameterizedSparqlString.toString());
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();
		while (results.hasNext()) {
			QuerySolution soln = results.next();
			 WikiLinkes = soln.get("Elements").toString();
		}
		return WikiLinkes;
	}

	// 请求返回 subject的结果
	public String Subject(String Keys) {
		String Subject = "null";
		String sparqlQueryString = "prefix category-ja: <http://ja.dbpedia.org/resource/Category:> "
				+ "prefix rdfs:	<http://www.w3.org/2000/01/rdf-schema#>"
				+ "prefix dcterms: <http://purl.org/dc/terms/>"
				// +
				// " select (group_concat(?str_subject; separator=\",\") as ?alt_name)"
				// + " where" + "{" + "dbpedia-ja:" + keys +
				// " dcterms:subject  ?alt_subject."
				+ " select (group_concat(?str_subject; separator=\",\") as ?alt_name)" + " where" + "{" + "?Keys" + " dcterms:subject  ?alt_subject."
				+ " ?alt_subject rdfs:label ?str_subject." + "}";
		ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(sparqlQueryString) {
			{
				setNsPrefix("dbpedia-ja", "http://ja.dbpedia.org/resource/");
			}
		};
		parameterizedSparqlString.setIri("?Keys", parameterizedSparqlString.getNsPrefixURI("dbpedia-ja") + Keys);
		System.out.println(Keys);
		Query query = QueryFactory.create(parameterizedSparqlString.toString());
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();
		while (results.hasNext()) {

			QuerySolution soln = results.next();
			String temp = soln.get("alt_name").toString();
			if (temp.isEmpty()) {
				Subject = "null";
			}
			else {
				Subject = temp;
			}
		}
		return Subject;
	}

	// 请求返回 redirect 和 redirectOF 的结果
	public String Redirect(String Keys) {
		String Redirect = "null";
		String RedirectOf = "null";

		String sparqlQueryString = "prefix dbpedia-owl: <http://dbpedia.org/ontology/>" + "prefix dbpedia-ja: <http://ja.dbpedia.org/resource/> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ " select (group_concat(?relabel; separator=\",\") as ?altrelabel) (group_concat(?relabelof; separator=\",\") as ?altrelabelof)" + " where" + "{{" + " ?Keys"
				+ " dbpedia-owl:wikiPageRedirects ?redirect." + "?redirect rdfs:label ?relabel }" + "union" + "{?redirectof dbpedia-owl:wikiPageRedirects " + " ?Keys"
				+ ".?redirectof rdfs:label ?relabelof  }" + "}";

		ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(sparqlQueryString) {
			{
				setNsPrefix("dbpedia-ja", "http://ja.dbpedia.org/resource/");
			}
		};
		parameterizedSparqlString.setIri("?Keys", parameterizedSparqlString.getNsPrefixURI("dbpedia-ja") + Keys);
		System.out.println(Keys);
		Query query = QueryFactory.create(parameterizedSparqlString.toString());
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();

		while (results.hasNext()) {
			// System.out.println(results.next());
			QuerySolution soln = results.next();

			if (!soln.get("altrelabel").toString().isEmpty()) {
				Redirect = soln.get("altrelabel").toString();
			}
			else {
				RedirectOf = soln.get("altrelabelof").toString();
			}

		}
		if (Redirect.equals("null")) {
			return RedirectOf;
		}
		else {
			System.out.println(Redirect);
			return Redirect;
		}

	}

	// 查找上下边界
	public ArrayList<String> Broader(String Keys) {

		ArrayList<String> resultArrayList = new ArrayList<String>();
		String Broader = "null";
		String BroaderOf = "null";

		String sparqlQueryString = "prefix category-ja: <http://ja.dbpedia.org/resource/Category:>" + "prefix dbpedia-ja: <http://ja.dbpedia.org/resource/> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + "prefix skos: <http://www.w3.org/2004/02/skos/core#>"
				+ " select (group_concat(?oneLabel; separator=\",\") as ?broader) (group_concat(?twoLabel; separator=\",\") as ?Broaderof)" + " where" + "{" + "{" + " ?Keys"
				+ " skos:broader ?one." + "?one rdfs:label ?oneLabel }" + "union" + "{?two skos:broader" + " ?Keys" + ".?two rdfs:label ?twoLabel  }" + "}";

		ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(sparqlQueryString) {
			{
				setNsPrefix("category-ja", "http://ja.dbpedia.org/resource/Category:");
			}
		};
		parameterizedSparqlString.setIri("?Keys", parameterizedSparqlString.getNsPrefixURI("category-ja") + Keys);
		System.out.println(Keys);
		Query query = QueryFactory.create(parameterizedSparqlString.toString());
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();

		while (results.hasNext()) {
			QuerySolution soln = results.next();
			if (!soln.get("broader").toString().isEmpty()) {
				resultArrayList.add(soln.get("broader").toString());
			}
			else {
				resultArrayList.add("Null");
			}
			if (!soln.get("Broaderof").toString().isEmpty()) {
				resultArrayList.add(soln.get("Broaderof").toString());
			}
			else {
				resultArrayList.add("Null");
			}
		}
		return resultArrayList;
	}

	// 根据wikilink 和subject 查询语义关系
	public ArrayList<List<String>> CheckSemanticRela(String Keys) {
		ArrayList<List<String>> relationArrayList = new ArrayList<List<String>>();
		String WikiLink = "";
		String Subject = "";
		String sparqlQueryString = "prefix category-ja: <http://ja.dbpedia.org/resource/Category:>" + "prefix dcterms: <http://purl.org/dc/terms/>"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + "prefix dbpedia-owl:     <http://dbpedia.org/ontology/>"
				+ "select ?wikiLink  (group_concat(?twoLabel; separator=\",\") as ?Subject)" + "where" + "{" + "?one dbpedia-owl:wikiPageWikiLink " + " ?Keys"
				+ ".?one rdfs:label ?wikiLink." + "?one dcterms:subject  ?two." + "?two rdfs:label ?twoLabel" + "}" + "Group By ?wikiLink";

		ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(sparqlQueryString) {
			{
				setNsPrefix("category-ja", "http://ja.dbpedia.org/resource/Category:");
			}
		};
		parameterizedSparqlString.setIri("?Keys", parameterizedSparqlString.getNsPrefixURI("category-ja") + Keys);
		System.out.println(Keys);
		Query query = QueryFactory.create(parameterizedSparqlString.toString());
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();
		while (results.hasNext()) {
			QuerySolution soln = results.next();
			WikiLink = soln.get("wikiLink").toString();
			Subject = soln.get("Subject").toString();
			List temp = new ArrayList<String>();
			temp.add(WikiLink);
			temp.add(Subject);
			relationArrayList.add(temp);
		}
		return relationArrayList;
	}
}
