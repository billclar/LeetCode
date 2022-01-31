package challenges.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * For more information about the Trie data structure: https://www.baeldung.com/trie-java
 * 
 * @author bill.clar
 *
 */
public class Trie
{
	private TrieNode root;

	public Trie()
	{
		root = new TrieNode();
	}

	public void insert(String word)
	{
		TrieNode current = root;

		for (char ch : word.toCharArray())
		{
			current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
		}

		current.setEndOfWord();
	}

	public boolean search(String word)
	{
		return search(word, true);
	}

	public boolean search(String word, boolean checkEndOfWord)
	{
		TrieNode current = root;

		for (char ch : word.toCharArray())
		{
			TrieNode node = current.getChildren().get(ch);
			if (node == null)
			{
				return false;
			}
			current = node;
		}

		if (checkEndOfWord)
		{
			return current.isEndOfWord();
		}
		else
		{
			return true;
		}
	}

	public boolean startsWith(String prefix)
	{
		return search(prefix, false);
	}

	class TrieNode
	{
		private Map<Character, TrieNode> children;
		private boolean endOfWord;

		public TrieNode()
		{
			children = new HashMap<>();
		}

		public Map<Character, TrieNode> getChildren()
		{
			return children;
		}

		public void setEndOfWord()
		{
			endOfWord = true;
		}

		public boolean isEndOfWord()
		{
			return endOfWord;
		}
	}
}
