package server.lib;

// TODO RES-BDD

//=============================================================================
// ▼ ConnexionBDD -- Renommez cela comme vous voulez
// ----------------------------------------------------------------------------
// Etablit une connexion avec la bdd.
// Cette classe étant abstraite, on n'établit qu'une seule connexion quand on
// lance le serveur, et on l'utilise à chaque fois qu'on en a besoin.
// TODO:
// - il faut trouver un moyen pour n'autoriser qu'une seule action sur la bdd
// à la fois (qu'une seule fonction sur l'objet connexion). Sauf s'il y a moyen
// qu'elle puisse en gérer plusieurs à la fois bien sûr...
//=============================================================================
public abstract class ConnexionBDD // renommer
{
	private static Type connexion; // Je sais pas le type ^^

	//---------------------------------------------------------------------------
	// * Connect
	// Initialise la connexion avec la bdd.
	//---------------------------------------------------------------------------
	public static connect()
	{
		// Votre connexion à la bdd
	}

	/*===========================================================================
	* ▼ CONCERNANT VOS CLASSES "OBJECTHANDLER"
	* ---------------------------------------------------------------------------
	* Dans vos classes ObjectHandler, passez vos fonctions sendObject en statique
	* pour qu'on puisse les appeler d'ici. Ainsi on aura pas à instancier d'objet.
	*
	* Pour rester dans la même logique, vous pouvez d'ailleurs les renommer
	* save(Object object).
	*
	* Par exemple:
	*
	* class QuestionHandler {
	*   public static void save(Question question)
	*   {
	* 		// Le contenu de votre fonction
	*   }
	* }
	===========================================================================*/
}
