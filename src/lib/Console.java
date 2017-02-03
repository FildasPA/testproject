package lib;

/**
 * Colors
 http://stackoverflow.com/a/5762502
 */
public class Console
{
	//---------------------------------------------------------------------------
	// * Command prompt
	//---------------------------------------------------------------------------
	public static void commandPrompt()
	{
		System.out.print("\r \n" + Ansi.GREEN + "λ " + Ansi.RESET);
	}

	//---------------------------------------------------------------------------
	// * Clear previous line
	//---------------------------------------------------------------------------
	public static void clearPreviousLine()
	{
		System.out.print("\r"); // Erase line content
		System.out.print(String.format("\033[%dA",1)); // Move up
	}

	//---------------------------------------------------------------------------
	// * Print
	//---------------------------------------------------------------------------
	public static void print(String message)
	{
		System.out.println(message);
	}

	//---------------------------------------------------------------------------
	// * Print
	//---------------------------------------------------------------------------
	public static void printb(String message)
	{
		clearPreviousLine();
		print(message);
		commandPrompt();
	}
}
