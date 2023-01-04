package parserr;

import java.util.logging.*;
import parserr.AST.*;
import parserr.Diff.*;

public class Parserr {
    private String token;
    private int lookahead;
    public static PolynomialExpr PE = new PolynomialExpr();
    public String mark = "+";


    public static enum Type{NUM,AXIS,ADD,SUB,DOT,POWER,EOF};
    public class Token{
        String symbol;
        Type type;
        
        
        public Token(String symbol,Type type){
            this.symbol = symbol;
            this.type = type;
        }
        
    }
    public Parserr(String token){
        this.token = token;
        lookahead = 0;
    }
    public Token getNextToken() throws Exception{
        if(lookahead>= token.length()){
            return new Token("",Type.EOF);
        }
        char ch = token.charAt(lookahead);
        if(ch >= '0' && ch <= '9'){return new Token(ch+"",Type.NUM);}
        if(ch == 'x' || ch == 'X'){return new Token(ch+"",Type.AXIS);}
        if(ch == '+'){return new Token(ch+"",Type.ADD);}
        if(ch == '-'){return new Token(ch+"",Type.SUB);}
        if(ch == '.'){return new Token(ch+"",Type.DOT);}
        if(ch == '^'){return new Token(ch+"",Type.POWER);}
        else{Error("unexpected token");}
        return null;
    }
    public void Error(String msg) throws Exception{
        throw new Exception(msg);
    }
    //Expr -> E $
    public AST.Expr Expr() throws Exception{
        Expr root = null;
        Token nt = getNextToken();
        //System.out.println(nt.symbol + " Expr tok");
         switch(nt.type){
            case NUM:
                root = E();
                //System.out.println(root + " root for Expr Num");
                eat(Type.EOF);
                break;
            case AXIS:
                root = E();
                //System.out.println(root + " root for Expr Axis");
                eat(Type.EOF);
                break;
            default:
                Error("Unexpected Token_Expr! " + nt);
                break;
        }
        return root;
        
    }
    //E -> T E'
    public AST.Expr E() throws Exception{
        Expr root = null,left;
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" E tok");
        switch(nt.type){
            case NUM:
                left = T();
                //System.out.println(left + " left for E Num");
                root = E_prime(left);
                //System.out.println(root + " root for E Num");
                break;
            case AXIS:
                left = T();
                //System.out.println(left + " left for E Axis");
                root = E_prime(left);
                //System.out.println(root + " root for E Axis");
                break;
            default:
                Error("Unexpected Token_E! "+ nt);
                break;
        }
        return root;
    }
    public AST.Expr E_prime(AST.Expr left) throws Exception{
        Expr root = null,right;
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" E' tok");
        switch(nt.type){
            case ADD:
                eat(Type.ADD);
                mark = "+";
                right = T();
                //System.out.println(right + " right for E' Add");
                root = new AST.AddExpr(left, right);
                root = E_prime(root);
                //System.out.println(root + " root for E' Add");
                break;
            case SUB:
                eat(Type.SUB);
                mark = "-";
                right = T();
                //System.out.println(right + " right for E' Sub");
                root = new AST.SubExpr(left, right);
                root = E_prime(root);
                //System.out.println(root + " root for E' Sub");
                break;
            case EOF:
                eat(Type.EOF);
                root = left;
                break;
            default:
                Error("Unexpected Token_E_prime!");
                break;
        }
        return root;
    }
    //T -> C T'    C-> A^P   A->x,num P->num,null
    public AST.Expr T() throws Exception{
        Expr root = null,left; 
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" T tok");
        switch(nt.type){
            case NUM:
                left = C();
                //System.out.println(left + " Left for T Num");
                root = T_prime(left);
                //System.out.println(root + " root for T Num");
                break;
            case AXIS:
                left = C();
                //System.out.println(left + " Left for T Axis");
                root = T_prime(left);
                //System.out.println(root + " root for T Axis");
                break;
            default:
                Error("Unexpected Token_T!");
                break;
        }
        return root;
    }
    public AST.Expr T_prime(AST.Expr left) throws Exception{
        AST.Expr root = null,right;
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" T' tok");
        switch(nt.type){
            case ADD:
                root = left;
                if(root.toString().charAt(0)=='x'||root.toString().charAt(0)=='X'){
                TermExpr TE_Add  = new TermExpr(Integer.parseInt(mark+1),Integer.parseInt(String.valueOf(root.toString().charAt(2))));
                PE.addTerm(TE_Add);
                }
                //System.out.println(root + " root for T' Add");
                break;
            case SUB:
                root = left;
                if(root.toString().charAt(0)=='x'||root.toString().charAt(0)=='X'){
                TermExpr TE_Sub  = new TermExpr(Integer.parseInt(mark+1),Integer.parseInt(String.valueOf(root.toString().charAt(2))));
                PE.addTerm(TE_Sub);
                }
                //System.out.println(root + " root for T' Sub");
                break;
            case NUM:
               //System.out.println(left + " left for T' Num");
               right = C();
               //System.out.println(right + " right for T' Num");
               root = new AST.DotExpr(left, right);
               TermExpr TE_Num  = new TermExpr(Integer.parseInt(mark+left.toString()),Integer.parseInt(String.valueOf(right.toString().charAt(2))));
               PE.addTerm(TE_Num);
               root = T_prime(root);
               //System.out.println(root + " root for T' Num");
               break;
            case AXIS:
               //System.out.println(left + " left for T' Axis");
               right = C();
               //System.out.println(right + " right for T' Axis");
               root = new AST.DotExpr(left, right);
               TermExpr TE_Axis  = new TermExpr(Integer.parseInt(mark+left.toString()),Integer.parseInt(String.valueOf(right.toString().charAt(2))));
               PE.addTerm(TE_Axis);
               root = T_prime(root);
               //System.out.println(root + " root for T' Axis");
               break;
            case EOF:
                eat(Type.EOF);
                root = left;
                //System.out.println(root + " root for C Eof");
                break;
            default:
                Error("Unexpected Token_T_prime!");
                break;
        }
        return root;
    }
    public AST.Expr C() throws Exception{
        Expr root=null,left,right;
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" C tok");
        switch(nt.type){
            case NUM:
                eat(Type.NUM);
                root = new AST.NumExpr(Integer.parseInt(nt.symbol));
                
                //System.out.println(root + " root for C Num");
                break;
            case AXIS:
                left = A();
                //System.out.println(left + " Left for A Axis");
                eat(Type.POWER);
                right = P();
                //System.out.println(right + " right for C Axis");
                root = new AST.PowerExpr(left,right);
        //        System.out.println(root + " root for C Axis");
                break;
            default:
                Error("Unexpected Token_C!");
                break;
        }
        return root;
    }
    public AST.Expr P() throws Exception{
        Expr root = null;
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" P tok");
        switch(nt.type){
            case NUM:
                eat(Type.NUM);
                root = new AST.NumExpr(Integer.parseInt(nt.symbol));
                break;
            default:
                Error("Unexpected Token_P!");
                break;
        }
        return root;
    }
       public AST.Expr A() throws Exception{
        Expr root = null;
        Token nt = getNextToken();
        //System.out.println(nt.symbol+" A tok");
        switch(nt.type){
            case AXIS:
                eat(Type.AXIS);
                root = new AST.AxisExpr(nt.symbol.charAt(0));
                break;
            default:
                Error("Unexpected Token_C!");
                break;
        }
        return root;
    }
    public void eat(Type type) throws Exception{
        Token tok = getNextToken();
        if(tok.type == type){
            lookahead++;
        }
        else{Error("Unexpected Token_eat!" + tok.symbol);}
    }
    
    public static void main(String[]args){
        String msg = "x^2-2x^1-1";
        Parserr p = new Parserr(msg);
       
        try {
           Expr root = p.Expr();
            System.out.println("Equation : "+root.toString());
            System.out.println("Evaluate : "+root.evaluate());
            System.out.println("Prefix : "+root.prefix());
            System.out.println("diffrentiate : "+PE.toString());
        }catch (Exception ex) {
            Logger.getLogger(Parserr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
