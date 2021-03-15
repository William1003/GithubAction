import java.math.BigInteger;

public class Term implements Comparable<Term>
{
    private BigInteger coe; //项的系数
    private BigInteger exp; //项的指数

    public Term(String coe, String exp) //字符串构造方法
    {
        this.coe = new BigInteger(coe);
        this.exp = new BigInteger(exp);
    }

    public Term(BigInteger coe, BigInteger exp) //BigInteger构造方法
    {
        this.coe = coe;
        this.exp = exp;
    }

    public Term derive() //求导，返回一个新的Term
    {
        Term derivation;
        if (exp.equals(new BigInteger("0"))) //如果指数为0，常数项，导数为0
        {
            derivation = new Term("0", "0");
        }
        else //指数不为0
        {
            BigInteger newCoe = coe.multiply(exp); //求导后新的系数
            BigInteger newExp = exp.subtract(new BigInteger("1")); //求导后新的指数
            derivation = new Term(newCoe, newExp);
        }
        return derivation;
    }

    public boolean hasSameExp(Term t) //是否有相同的指数
    {
        boolean result = false;
        if (t.getExp().equals(exp))
        {
            result = true;
        }
        return result;
    }

    public BigInteger getExp()
    {
        return exp;
    }

    public BigInteger getCoe()
    {
        return coe;
    }

    public void add(Term t) throws DifferentExp //合并同类项，若非同类相相加，抛出异常
    {
        if (hasSameExp(t))
        {
            coe = coe.add(t.getCoe());
        }
        else
        {
            throw new DifferentExp();
        }
    }

    @Override
    public int compareTo(Term o)
    {
        if (coe.signum() == 1 && o.getCoe().signum() != 1)
        {
            return -1;
        }
        return exp.compareTo(o.getExp());
    }

    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        boolean condition1 = coe.abs().equals(new BigInteger("1"));//系数是否为+-1
        boolean condition2 = exp.equals(new BigInteger("0"));//指数是否为0
        boolean condition3 = exp.equals(new BigInteger("1"));//指数是否为1
        if (coe.signum() == -1)//判断项的符号
        {
            output.append("-");
        }
        else
        {
            output.append("+");
        }
        if (condition1 && condition2) //系数为+-1，指数为0
        {
            output.append("1");
        }
        if (condition1 && !condition2) //系数为+-1，指数不为0
        {
            if (!condition3)//指数不为1
            {
                output.append("x**" + exp);
            }
            else
            {
                output.append("x");
            }
        }
        if (!condition1 && condition2) //系数不为+-1，指数为0
        {
            output.append(coe.abs());
        }
        if (!condition1 && !condition2) //系数不为+-1，指数不为0
        {
            if (!condition3)//指数不为1
            {
                output.append(coe.abs() + "*x**" + exp);
            }
            else
            {
                output.append(coe.abs() + "*x");
            }
        }
        return output.toString();
    }

}


