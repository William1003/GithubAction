import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Poly //多项式类
{
    private ArrayList<Term> termList = new ArrayList<>();//用ArrayList存储Term

    public void addTerm(Term t) //加入新的项，合并同类项，根据指数排序
    {
        if (t != null)
        {
            boolean flag = false; //是否被合并的标记
            if (!t.getCoe().equals(new BigInteger("0")))
            {
                for (Term term : termList)
                {
                    if (term.hasSameExp(t)) //如果发现相同指数项，合并同类项
                    {
                        flag = true;
                        try
                        {
                            term.add(t);
                        } catch (DifferentExp differentExp)
                        {
                            System.out.println(
                                    "Add Terms with Different Exception!");
                            differentExp.printStackTrace();
                        }
                        if (term.getCoe().equals(new BigInteger("0")))
                        //如果相加后系数为0，删除该项
                        {
                            termList.remove(term);
                        }
                    }
                    if (flag)
                    {
                        break;
                    }
                }
                if (!flag) //没有发现同类项，将t加入列表
                {
                    termList.add(t);
                }
                //Collections.sort(termList); //对列表根据指数进行排序
            }
        }
    }

    public Poly derive() //多项式求导
    {
        Poly derivation = new Poly();
        for (Term term : termList) //对每一个Term求导，加入导函数中
        {
            derivation.addTerm(term.derive());
        }
        return derivation;
    }

    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder("");
        Collections.sort(termList);
        for (Term term : termList)
        {
            output.append(term.toString());
            //output.append(" ");
        }
        if (output.length() > 0)
        {
            if (output.charAt(0) == '+')
            {
                output.deleteCharAt(0);
            }
        }
        else
        {
            output.append("0");
        }
        return output.toString();
    }
}
