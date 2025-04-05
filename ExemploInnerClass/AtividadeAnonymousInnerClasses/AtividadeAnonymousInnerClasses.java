package ExemploInnerClass.AtividadeAnonymousInnerClasses;

final class AtividadeAnonymousInnerClasses { 
    public void dolt(Runnable runnable){
        runnable.run();
    }

    static interface Runnable{
        public void run();
    }

    public static void main(String[] args) {
        var atividade = new AtividadeAnonymousInnerClasses();

        atividade.dolt(new AtividadeAnonymousInnerClasses.Runnable(){
            @Override
            public void run(){
                System.out.println("Executando!");
            }
        });
    }
}
