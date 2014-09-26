(ns clojure_tictac.game-setup-spec
  (:require [speclj.core :refer :all]
  					[clojure_tictac.ttt-rules :refer [x o]]
            [clojure_tictac.console :refer :all]
            [clojure_tictac.board :refer :all]
            [clojure_tictac.game-setup :refer :all]
            [clojure_tictac.players :refer [move]]))

(describe "game setup"
	(def human-first (clojure_tictac.players.Human. x))
	(def human-last (clojure_tictac.players.Human. o))
	(def comp-first (clojure_tictac.players.Computer. x))
	(def comp-last (clojure_tictac.players.Computer. o))
	(def players [human-first comp-last])
	(def empty-board (make-board 9))
	(def full-board [x o x
									 x o x
									 o x o])
	(def test-board [x  o  x
									 x  o	 x
									 o nil o])

	(it "creates the players"
		(should= [human-first comp-last]
							(player-list 1))
		(should= [comp-first human-last]
							(player-list 2))
		(should= [human-first human-last]
							(player-list 3)))

	(it "switches players"
		(should= comp-last
							(switch-player [human-first comp-last] human-first))
		(should= human-first
							(switch-player [human-first comp-last] comp-last)))

	(it "shows correct game conclusion"
		(should= "It's a draw!\n" 
						(with-out-str (show-game-conclusion [x o x
																								 x o x
																								 o x o])))
		(should= "x wins!\n"
						(with-out-str (show-game-conclusion [x x x
																								 o o nil
																								nil nil nil]))))
	(it "says bye! and ends game"
		(should= "Bye!\n"
						(with-out-str (end-game)))
		(should-invoke shutdown-agents {:times 1}
									(end-game)))

	(it "shows game menu"
		(should= (str (borderline) "\n1 - Human vs Computer\n2 - Computer vs Human\n3 - Human vs Human\n4 - Computer vs Computer\n0 - Quit\n" (borderline) "\n")
							(with-out-str (show-game-menu))))

	(context "starts a game"
		(it "shows the board"
			(should-invoke show-board {:times 1}
										(start players full-board)))

		(it "shows game conclusion if game is over"
			(should-invoke show-game-conclusion {:times 1}
										(start players full-board)))

		(it "shows game conclusion after asking for move"
			(should= "It's a draw!"
				(re-find #"It's a draw!" (with-out-str (with-in-str "7" (start players test-board)))))))
)