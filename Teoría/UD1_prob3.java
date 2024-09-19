class A {
    private static int contador = 0;
    public A() {
        contador++;
    }

    public static int GetInstances() {
        return contador;
    }

    @Override
    protected void finalize() throws Throwable {
        // Donde puedo ejecutar algo cuando se vaya a eliminar el objeto
        count--;
    }
}
