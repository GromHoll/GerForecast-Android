package edu.nntu.gerforecast.math.data;

public class ElasticityOutput {

    private final OutputValues q;
    private final OutputValues k;
    private final OutputValues ks;
    private final OutputValues l;
    private final OutputValues f;

    public ElasticityOutput(OutputValues q, OutputValues k, OutputValues ks, OutputValues l, OutputValues f) {
        this.q = q;
        this.k = k;
        this.ks = ks;
        this.l = l;
        this.f = f;
    }

    public OutputValues getQ() {
        return q;
    }

    public OutputValues getK() {
        return k;
    }

    public OutputValues getKs() {
        return ks;
    }

    public OutputValues getL() {
        return l;
    }

    public OutputValues getF() {
        return f;
    }
}
