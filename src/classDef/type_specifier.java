package classDef;


import java.util.Vector;

import core.env;
import core.first;
import core.symbol;
import classes.*;
public class type_specifier extends root
{
	public type_specifier(){}
	@SuppressWarnings("unchecked")
	public int check() throws Exception
	{
		returnrecord r = new returnrecord();
		
		if(child.size()==1)
		{
						
			if(((String)child.get(0).record).equals("int"))
			{
				r.rtype = new Tint();
				record = r;
			}
			if(((String)child.get(0).record).equals("void"))
			{
				r.rtype = new Tvoid();
				record = r;
			}
			if(((String)child.get(0).record).equals("char"))
			{
				r.rtype = new Tchar();
				record = r;
			}
		}
		else if(child.size()>2)
		{
			//union?...=struct
			
				String s = "";
				root son = child.get(1);
				if (son.check()!=0) throw new Exception();
				
				if(son instanceof identifier)
				{
					s += (String)((returnrecord)son.record).value;
				}
				else
					s += "Legend_" + String.valueOf(first.noname++);
				
				env newe = new env();
				first.e.add(newe);
				
				//inverse...
				Vector<type> vt = new Vector<type>();
				Vector<String> vs = new Vector<String>();
				for(int i=1+(child.size()-1)%2;i<child.size();i+=2)
				{
					if(child.get(i).check()!=0)throw new Exception();
					type t = ((returnrecord)child.get(i).record).rtype;
					if(child.get(i+1).check()!=0)throw new Exception();
					Vector<String> v = (Vector<String>)child.get(i+1).record;
					for(int j=0;j<v.size();j++)if(first.e.lastElement().functable.get(symbol.symbol(v.get(j)))!=null)throw new Exception();
					Vector<pair> p = (Vector<pair>)child.get(i+1).check(t);
					for(int j=0;j<p.size();j++)
					{
						vt.add(p.get(j).typ);
						vs.add(p.get(j).str);
						first.e.lastElement().functable.put(symbol.symbol(p.get(j).str),p.get(j).typ);
					}
									
				}
				
				first.e.remove(first.e.size()-1);
				
				type tmp = (type)first.e.lastElement().structtable.get(symbol.symbol(s));
				if (tmp != null)throw new Exception();
				type struct = new struct(s, vt, vs);
				
				first.e.lastElement().structtable.put(symbol.symbol(s), struct);

				r.rtype = struct;
				record = r;
				
				
		}
		else
		{
				if (child.get(1).check()!=0) throw new Exception();
				String s = (String)((returnrecord)child.get(1).record).value;
				type struct = find(s);
				
				
				if (struct == null)struct = new name(s);
				
				
				r.rtype = struct;
				record = r;

				
				
		}
		return 0;
	}
}