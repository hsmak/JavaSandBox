package _ocp8.ch01_declaration_access_enum;


class ObjectWorkflowOrderRunner {

    String name = "default name";

    {
        System.out.println("init block - instance: " + name);
    }

    static {
        System.out.println("init block - static/class");
    }

    public ObjectWorkflowOrderRunner() {
        System.out.println(name);
        System.out.println("constructor");
        name = "another name";
    }

    public static void main(String[] args) {
        new ObjectWorkflowOrderRunner();
    }
}

