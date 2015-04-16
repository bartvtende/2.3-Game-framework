package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

public class OthelloAIThreaded implements Runnable {
	private OthelloBoard board;							// Playing field
	private char runForPlayer;
	
	private ArrayList<OthelloMove> subThreadResults; 	// [Used in root AI] Stores the results of each move
	private OthelloAI[] aiInstances; 					// [Used in root AI] Stores AI instances so the thread each AI instance can be retrieved from it
	
	public OthelloAIThreaded(OthelloBoard board) {
		this.board = board;
	}

	public synchronized void addResult(OthelloMove result) {
		subThreadResults.add(result);
	}
	
	public void getBestMove(char player) {
		runForPlayer = player;
		char opponent = 0;
		if (player == 'X') {
			opponent = 'O';
		} else {
			opponent = 'X';
		}
		ArrayList<OthelloMove> moves = board.getValidMoves(player);
		
		// Iterate over all available moves
		
		aiInstances = new OthelloAI[moves.size()];
		subThreadResults = new ArrayList<OthelloMove>();
		
		for(int i = 0; i < moves.size(); i++) {
			OthelloMove move = moves.get(i);
			
			OthelloBoard boardClone = board.clone();
			boardClone.place(player, move);
			OthelloAI aiInstance = new OthelloAI(boardClone);
			aiInstance.setRootAIInstance(this);
			aiInstances[i] = aiInstance;
			aiInstance.getBestMove(opponent, move);
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		for(int i = 0; i < aiInstances.length; i++) {
			aiInstances[i].waitForCompletion();
		}
		
		OthelloMove bestMove = null;
		// At this point, all threads are done. Figure out the best one
		synchronized(subThreadResults) {
			for(OthelloMove candidate : subThreadResults) {
				if(bestMove == null){
					bestMove = candidate;
					continue;
				}
				if(!bestMove.isBetterThan(candidate, runForPlayer)) {
					bestMove = candidate;
				}
			}
		}
		System.out.println("FOUND A MOTHERFUCKER: " + bestMove);
	}

}
