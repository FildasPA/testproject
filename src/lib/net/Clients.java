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
	// * Close all clients
	//---------------------------------------------------------------------------
	public static void closeAll()
	{
		for(Map.Entry<Integer,ClientHandler> entry : clients.entrySet())
			entry.getValue().close();
	}

	//---------------------------------------------------------------------------
	// * Get client by id - pas forcément nécessaire, je le laisse là au cas
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
