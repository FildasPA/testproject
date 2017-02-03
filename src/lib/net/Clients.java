package lib.net;

import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lib.net.ClientHandler;

//=============================================================================
// ▼ Clients
// ----------------------------------------------------------------------------
// Gère la liste des clients connectés.
//=============================================================================
abstract class Clients
{
	private static Integer clientsNumber = 0; // nombre de clients qui se sont connectés
	private static Map<Integer,ClientHandler> clients = new
		ConcurrentHashMap<Integer,ClientHandler>(); // liste des clients connectés

	//---------------------------------------------------------------------------
	// * Add client
	//---------------------------------------------------------------------------
	public static Integer add(ClientHandler client)
	{
		clients.put(clientsNumber,client);
		return clientsNumber++;
	}

	//---------------------------------------------------------------------------
	// * Remove client
	//---------------------------------------------------------------------------
	public static void remove(Integer clientId)
	{
		clients.remove(clientId);
	}

	//---------------------------------------------------------------------------
	// * Send object to client
	//---------------------------------------------------------------------------
	public static void sendObjectToClient(Integer clientId, Object object)
	{
		Clients.sendObjectToClient(clientId,object);
	}

	//---------------------------------------------------------------------------
	// * Get client by id
	// NOTE: Fonction pas forcément nécessaire, je la laisse là au cas où...
	//---------------------------------------------------------------------------
	// public ClientHandler getClientById(Integer id)
	// {
	// 	return clients.get(id);
	// }

	//---------------------------------------------------------------------------
	// * Display clients
	// Affiche la liste des clients connectés.
	//---------------------------------------------------------------------------
	public static void display()
	{
		System.out.println("Liste des clients connectés");
		System.out.println(clients.entrySet().toString());
	}
}
