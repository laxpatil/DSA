package leetcode;
import java.util.LinkedList;


public class trieNode2{
        char data;
        LinkedList<trieNode2> childList;
        boolean end;
        
        
        
        public trieNode2(char c)
        {
        	
        	
        	childList=new LinkedList<trieNode2>();
        	end=false;
        	data=c;
        	
        }
        
        public trieNode2 getsubNode(char c)
        {
        	if (childList !=null)
        	{
        		for (trieNode2 d: childList)
        		{
        			if (d.data==c)
        			{
        				return d;
        			}
        		}
        	}
        	
        	return null;
        }
        
        
        
    }
    