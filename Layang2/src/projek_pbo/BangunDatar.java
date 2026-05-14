package projek_pbo;

public abstract class BangunDatar implements BendaGeometri {
    protected double luas;
    protected double keliling;

    public BangunDatar() {}

    @Override
    public abstract double hitungLuas();
    
    @Override
    public abstract double hitungKeliling();
}
