class lexer{
    public static void lex(String data) {
            System.out.println(data.split(","));
    }
    public static void main(String[] args) {
        lexer l = new lexer(System.in);
        l.lex();
    }
}