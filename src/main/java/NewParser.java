import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewParser
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        if (in.hasNextLine())
        {
            String line = in.nextLine();
            Poly poly = parser(line);
            System.out.println(poly.derive());
            System.out.println(poly);
        }
        else
        {
            System.out.println("Wrong Format!");
        }
    }

    public static Poly parser(String line)
    {
        Poly poly = new Poly();
        if (line != null)
        {
            String s = "+" + line.replaceAll("[ \t]", "");
            String sign = "(?<sign>[+-]{1,2})";
            String coe = "((?<coe>\\d+)\\*)?";
            String exp = "(\\*\\*(?<exp>[+-]?\\d+))?";
            String termString = sign + coe + "x" + exp;
            Pattern p = Pattern.compile(termString);
            Matcher m = p.matcher(s);
            while (m.find())
            {
                BigInteger coeValue = coeParser(m.group("sign"), m.group("coe"));
                BigInteger expValue = expParser(m.group("exp"));
                Term term = new Term(coeValue, expValue);
                poly.addTerm(term);
            }
        }
        return poly;
    }

    public static BigInteger coeParser(String sign, String coe)
    {
        BigInteger coeValue;
        if (coe == null)
        {
            coeValue = new BigInteger("1");
        }
        else
        {
            coeValue = new BigInteger(coe);
        }
        if (sign.equals("+-") || sign.equals("-+") || sign.equals("-"))
        {
            coeValue = coeValue.multiply(new BigInteger("-1"));
        }
        return coeValue;
    }

    public static BigInteger expParser(String exp)
    {
        BigInteger expValue;
        if (exp == null)
        {
            expValue = new BigInteger("1");
        }
        else
        {
            expValue = new BigInteger(exp);
        }
        return expValue;
    }
}
