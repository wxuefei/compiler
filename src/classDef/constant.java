package classDef;

public class constant extends root
{
	public constant(){}
	public int check() throws Exception
	{
		if(child.get(0).check()!=0)
		{
			throw new Exception();
		}
		record = child.get(0).record;
		return 0;
	}
}
