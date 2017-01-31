package server;

import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//=============================================================================
// ▼ Clients
// ----------------------------------------------------------------------------
// Gère la liste des clients connectés.
//=============================================================================
public class Clients
{
	private static Integer clientsNumber = 0; // nombre de clients qui se sont connectés
	private static Map<Integer,ClientHandler> clients = new
		ConcurrentHashMap<Integer,ClientHandler>(); // liste des clients connectés

	//---------------------------------------------------------------------------
	// * Add client
	//---------------------------------------------------------------------------
	public void add(Integer clientId, ClientHandler client)
	{
		clients.put(clientId,client);
	}

	//---------------------------------------------------------------------------
	// * Remove client
	//---------------------------------------------------------------------------
	public void remove(Integer clientId)
	{
		clients.remove(clientId);
	}

	//---------------------------------------------------------------------------
	// * Send object to client
	//---------------------------------------------------------------------------
	public void sendObjectToClient(Integer clientId, Object object)
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
}
