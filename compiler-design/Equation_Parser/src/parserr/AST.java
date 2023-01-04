package parserr;

import parserr.Diff.TermExpr;

public class AST {
    public static abstract class Expr{
        public abstract int evaluate();
        public abstract String prefix();
    }
    public static class AddExpr extends Expr{
        Expr right,left;
        public AddExpr(Expr left,Expr right){
            this.left = left;
            this.right = right;
        }
        @Override
        public int evaluate() {
            return left.evaluate() + right.evaluate();
        }
        @Override
        public String toString() {
            return this.left.toString() + "+" + this.right.toString();
        }
        @Override
        public String prefix() {
            return "+" + left.prefix() + right.prefix();
        }
    }
    public static class SubExpr extends Expr{
        Expr right,left;
        public SubExpr(Expr left,Expr right){
            this.left = left;
            this.right = right;
        }
        @Override
        public int evaluate() {
            return left.evaluate() - right.evaluate();
        }
        @Override
        public String toString() {
            return this.left.toString() + "-" + this.right.toString();
        }
        @Override
        public String prefix() {
            return "-" + left.prefix() + right.prefix();
        }
    }
    public static class NumExpr extends Expr{
        int val;
        public NumExpr(int val){
            this.val = val;
        }
        @Override
        public int evaluate() {
           return val;
        }
        @Override
        public String toString() {
            return this.val + "";
        }
        @Override
        public String prefix() {
            return this.val + "";
        }
    }
    public static class AxisExpr extends Expr{
        char axis;
        public AxisExpr(char axis){
            this.axis = axis;
        }
        @Override
        public int evaluate() {
            return axis;
        }
        @Override
        public String toString() {
            return this.axis + "";
        }
        @Override
        public String prefix() {
            return this.axis + "";
        }
    }
    public static class PowerExpr extends Expr {
        Expr left;
        Expr right;
        public PowerExpr(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }
        @Override
        public int evaluate() {
            return left.evaluate() ^ right.evaluate();
        }
        @Override
        public String toString() {
            
            return this.left.toString() + "^" + this.right.toString();
        }
        @Override
        public String prefix() {
            return "^" + left.prefix() + right.prefix();
        }
    }
    public static class DotExpr extends Expr{
        Expr right,left;
        public DotExpr(Expr left,Expr right){
            this.left = left;
            this.right = right;
            
        }
        @Override
        public int evaluate() {
            return left.evaluate() * right.evaluate();
        }
        @Override
        public String toString() {
            return this.left.toString() + "" + this.right.toString();
        }
        @Override
        public String prefix() {
            return "" + left.prefix() + right.prefix();
        }
    }
    
}
