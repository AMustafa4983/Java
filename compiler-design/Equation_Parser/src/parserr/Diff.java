/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserr;
import java.util.ArrayList;
/**
 *
 * @author Abdelrahman
 */
public class Diff {
    public static abstract class  Expression
    {
	public abstract void  diffrentiate();
    }
public static class PolynomialExpr extends Expression{
	ArrayList<TermExpr> allTerms;

	public PolynomialExpr(){
		allTerms = new ArrayList<>();
	}

	public void addTerm(TermExpr term){
		allTerms.add(term);
	}

        @Override
	public String toString(){
		String msg = "";
		for(TermExpr t : allTerms)
			msg = msg + t.toString();
		return msg;
	}

        @Override
	public void diffrentiate(){
		for(TermExpr t : allTerms)
			t.diffrentiate();
	}
    }
    public static class TermExpr extends Expression
{
	int c;  //coefficient
	int p;  //power

	public TermExpr(int c, int p){
		this.c = c;
		this.p = p;
                diffrentiate();
                toString();
	}
        @Override
	public String toString(){
		if(c == 0)
			return "";
		if(c == 1 && p == 1)
			return "+x";
		else if(c == -1 && p == 1)
			return "-x";
		else if(c > 0 && p == 0)
			return "+" + c;
		else if(c < 0 && p == 0)
			return ""  + c;
		else if(c > 0 && p == 1)
			return "+"+c+"x";
		else if(c < 0 && p == 1)
			return c+"x";
		else if(c > 0)
			return "+"+c+"x^"+p;
		else
			return c+"x^"+p;
	}
        @Override
	public void diffrentiate(){
		this.c = this.c * p;
		this.p = this.p - 1;
	}
    }
}
