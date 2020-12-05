package solver;

public class ComplexNumber {

    float realPart = 0.0f;
    float imaginaryPart = 0.0f;

    ComplexNumber(float realPart, float imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public String toString() {
        String realPartString = "";
        String imaginaryPartString = "";
        String sign = "";
        if (realPart != 0.0f) {
            realPartString = String.valueOf(realPart);
        }
        /*if (imaginaryPart != 0.0) {
            imaginaryPartString = String.valueOf(imaginaryPart);
           // sign = "+";
        }*/
        if (imaginaryPart == -1.0) {
            sign = "-";
        }

        return  realPartString  + sign + imaginaryPartString + "i ";
    }

    @Override
    public boolean equals(Object obj) {

        ComplexNumber object = (ComplexNumber) obj;

        return this.realPart == object.realPart && this.imaginaryPart == object.imaginaryPart;
    }
}
