package challenges.daily;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * 
 * @author bill.clar
 *
 */
public class AddTwoNumbers
{
	public static ListNode addTwoNumbers(ListNode list1, ListNode list2)
	{
		ListNode result = null;
		ListNode prev = null;
		ListNode curr1 = list1;
		ListNode curr2 = list2;

		int carryOver = 0;

		while (curr1 != null || curr2 != null)
		{
			int value = 0;

			if (curr1 == null)
			{
				value = curr2.val;
				curr2 = curr2.next;
			}
			else if (curr2 == null)
			{
				value = curr1.val;
				curr1 = curr1.next;
			}
			else
			{
				value = curr1.val + curr2.val;
				curr1 = curr1.next;
				curr2 = curr2.next;
			}

			value += carryOver;

			carryOver = value / 10;
			value %= 10;

			ListNode next = new ListNode(value);
			if (result == null)
			{
				result = next;
			}
			else
			{
				prev.next = next;
			}

			prev = next;
		}

		if (carryOver > 0)
		{
			ListNode next = new ListNode(carryOver);
			prev.next = next;
		}

		return result;
	}

	public static void main(String[] args)
	{
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		ListNode result = addTwoNumbers(l1, l2);

		while (result != null)
		{
			System.out.println(result.val);

			result = result.next;
		}
	}
}

class ListNode
{
	int val;
	ListNode next;

	ListNode()
	{
	}

	ListNode(int val)
	{
		this.val = val;
	}

	ListNode(int val, ListNode next)
	{
		this.val = val;
		this.next = next;
	}
}
