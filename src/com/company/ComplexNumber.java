package com.company;


public class ComplexNumber {

    private double re;
    private double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    private double getModule() {
        return Math.sqrt(this.re * this.re + this.im * this.im);
    }

    public static ComplexNumber sum(ComplexNumber cn1, ComplexNumber cn2) {
        return new ComplexNumber(cn1.getRe() + cn2.getRe(), cn1.getIm() + cn2.getIm());
    }

    public static ComplexNumber multiply(ComplexNumber cn1, ComplexNumber cn2) {
        return new ComplexNumber(cn1.getRe() * cn2.getRe() - cn1.getIm() * cn2.getIm(), cn1.getRe() * cn2.getIm() + cn1.getIm() * cn2.getRe());
    }

    public static ComplexNumber subtract(ComplexNumber cn1, ComplexNumber cn2) {
        return new ComplexNumber(cn1.getRe() - cn2.getRe(), cn1.getIm() - cn2.getIm());
    }

    public static ComplexNumber divide(ComplexNumber cn1, ComplexNumber cn2) {
        ComplexNumber temp = new ComplexNumber(cn2.getRe(), (-1) * cn2.getIm());
        temp = ComplexNumber.multiply(cn1, temp);
        double denominator = cn2.getRe() * cn2.getRe() + cn2.getIm() * cn2.getIm();
        return new ComplexNumber(temp.getRe() / denominator, temp.getIm() / denominator);
    }

    private double GetArg() {
        if (this.re > 0) {
            return Math.atan(im / re);
        } else {
            if (re < 0 && im > 0) {
                return Math.PI + Math.atan(im / re);
            } else {
                return -Math.PI + Math.atan(im / re);
            }
        }
    }

    public static ComplexNumber pow(ComplexNumber cn, int power) {
        double factor = Math.pow(cn.getModule(), power);
        return new ComplexNumber(factor * Math.cos(power * cn.GetArg()), factor * Math.sin(power * cn.GetArg()));
    }

    public static ComplexNumber[] sqrt(ComplexNumber cn) {
        double a = cn.getModule() / 2;
        ComplexNumber pos = new ComplexNumber(Math.sqrt(a + cn.getRe() / 2), Math.signum(cn.getIm()) * Math.sqrt(a - cn.getRe() / 2));
        ComplexNumber neg = new ComplexNumber((-1) * pos.getRe(), (-1) * pos.getIm());
        ComplexNumber[] answer = {pos, neg};
        return answer;
    }

    private String sign() {
        if (im > 0) return " + ";
        else return " - ";
    }

    @Override
    public String toString() {
        String string;
        if (im == 1 || im == -1) {
            if (re == 0) {
                string = sign() + "i";
            } else {
                string = Double.toString(re) + sign() + "i";
            }
        } else {
            string = Double.toString(re) + sign() + Double.toString(Math.abs(im)) + "i";
        }
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass() || obj == null)
            return false;
        return true;
    }

    public static void main(String[] args) {
        ComplexNumber x = new ComplexNumber(2, 3);
        ComplexNumber y = new ComplexNumber(-1, 2);
        System.out.println("z1 = " + x + ",     z2 = " + y);

        ComplexNumber z;
        z = ComplexNumber.sum(x, y);
        System.out.println("+ : " + z);

        z = ComplexNumber.subtract(x, y);
        System.out.println("- : " + z);

        z = ComplexNumber.divide(x, y);
        System.out.println("/ : " + z);

        z = ComplexNumber.multiply(x, y);
        System.out.println(" * :" + z);

        z = ComplexNumber.pow(y, 2);
        System.out.println("Pow 2 of z2 : " + z);

        ComplexNumber b = new ComplexNumber(3, 4);
        ComplexNumber[] ans = ComplexNumber.sqrt(b);
        System.out.println("Sqrt of " + b + " = " + ans[0] + ",  " + ans[1]);

    }
}
