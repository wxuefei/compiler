package classDef;

public class expression extends root
{
	public expression(){}
	public int check() throws Exception
	{
		for(int i=0;i<child.size();i++)
		{
			if(child.get(i).check()!=0)throw new Exception();
			if(i==0)record = child.get(i).record;			
		}
		
		return 0;
	}
}