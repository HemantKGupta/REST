package com.hkg.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hkg")
public class CustomEndPoint {

	//public static long filePointer;

	@OnMessage
	public void onMessage(Session session, String msg) {
		try {
			System.out.println("received msg " + msg + " from "
					+ session.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnOpen
	public void open(Session session) {
		try {
			session.getBasicRemote().sendText(WatchServiceUtility.loadFile());
			WatchServiceUtility.watchDir("/home/hemantg/Desktop/testdir", session);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("New session opened: " + session.getId());
	}

	@OnError
	public void error(Session session, Throwable t) {
		System.err.println("Error on session " + session.getId());
	}

	@OnClose
	public void closedConnection(Session session) {
		System.out.println("session closed: " + session.getId());
	}


}
