package leetcode;

public class trie {
private trieNode2 root;
boolean flag=false;

public trie()
{
    root = new trieNode2(' '); 
}
public boolean search( String word)
{
	trieNode2 current=root;
	
	for (char ch: word.toCharArray())
	{
		if (current.getsubNode(ch)==null)
			return false;
		else
			current=current.getsubNode(ch);
		
		
	}
	
	if (current.end==true)
		return true;
	
	return false;
	
}


public void insert(String word)
{
	trieNode2 current=root;
	
	for(char ch: word.toCharArray())
	{
		if (current.getsubNode(ch)==null)
		{
			if (current.end==true)
			{
				System.out.println("BAD SET");
				flag=true;
				System.out.println(word); ///to find the prefix of the another word
				return;
			}
			current.childList.add(new trieNode2(ch));
			current=current.getsubNode(ch);
		}
		else
			current=current.getsubNode(ch);
		
		
		
	}
	
	current.end=true;
	
	
	
}




}
