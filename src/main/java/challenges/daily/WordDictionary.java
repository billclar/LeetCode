package challenges.daily;

import java.util.HashMap;
import java.util.Map;

class WordDictionary
{
	Trie dictionary;

	public WordDictionary()
	{
		dictionary = new Trie();
	}

	public void addWord(String word)
	{
		dictionary.insert(word);
	}

	public boolean search(String word)
	{
		return dictionary.search(word);
	}

	class Trie
	{
		private TrieNode root;

		public Trie()
		{
			root = new TrieNode();
		}

		public void insert(String word)
		{
			if (!word.matches("[a-z]+"))
			{
				throw new IllegalArgumentException("Lower case letters only");
			}

			TrieNode current = root;

			for (char ch : word.toCharArray())
			{
				current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
			}

			current.setEndOfWord();
		}

		/**
		 * 'word' consists of lowercase letters and periods (.). Periods are wild cards and can match any character.
		 * 
		 * @param word
		 * @return
		 */
		public boolean search(String word)
		{
			System.out.println("search('" + word + "')");
			return search(word, root);
		}

		public boolean search(String word, TrieNode start)
		{
			System.out.println("search('" + word + "', " + start.toString() + ")");
			TrieNode current = start;

			for (char ch : word.toCharArray())
			{
				if (ch == '.')
				{
					// Check for the end of the search word so that we don't break the substring calculation.
					int index = word.indexOf('.');
					boolean endOfSearchWord = (index + 1 == word.length());
					String substring = (endOfSearchWord ? null : word.substring(index + 1, word.length()));
					System.out.println("endOfSearchWord = " + endOfSearchWord + ", substring = '" + substring + "'");

					for (TrieNode child : current.getChildren().values())
					{
						if (endOfSearchWord)
						{
							if (child.isEndOfWord())
							{
								return true;
							}
						}
						else
						{
							if (search(substring, child))
							{
								return true;
							}
						}
					}

					return false;
				}
				else
				{
					TrieNode node = current.getChildren().get(ch);
					if (node == null)
					{
						return false;
					}
					current = node;
				}
			}

			return current.isEndOfWord();
		}
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

		@Override
		public String toString()
		{
			return "TrieNode [Children: " + children.keySet() + ", endOfWord = " + endOfWord + "]";
		}
	}

	public static void main(String[] args)
	{
		WordDictionary dictionary = new WordDictionary();
		dictionary.addWord("at");
		dictionary.addWord("and");
		dictionary.addWord("an");
		dictionary.addWord("add");

		// System.out.println(dictionary.search("a"));
		// System.out.println(dictionary.search(".at"));

		dictionary.addWord("bat");

		System.out.println(dictionary.search(".at"));
		// System.out.println(dictionary.search("an."));
		// System.out.println(dictionary.search("a.d."));
		// System.out.println(dictionary.search("b."));
		// System.out.println(dictionary.search("a.d"));
		// System.out.println(dictionary.search("."));
	}
}