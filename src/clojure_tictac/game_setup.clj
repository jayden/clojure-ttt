(ns clojure_tictac.game-setup
	(:require [clojure_tictac.console :refer :all]
						[clojure_tictac.board :refer :all]
						[clojure_tictac.players :refer :all]
						[clojure_tictac.ttt-rules :refer :all]))

(def default-board-size 9)
(def game-types [["Human" "Computer"] ["Computer" "Human"] ["Human" "Human"] ["Computer" "Computer"]])

(defn player-list [game-type]
	(let [type (- game-type 1)
				player1 (first (nth game-types type))
				player2 (second (nth game-types type))]
		[(create-player player1 x) (create-player player2 o)]))

(defn switch-player [player-list current-player]
	(if (= (first player-list) current-player)
		(second player-list)
		(first player-list)))

(defn show-game-conclusion [board]
	(if (winner? board)
		(show-message (str (game-winner board) " wins!"))
		(show-message "It's a draw!")))

(defn start [player-list new-board]
    (loop [board new-board
           current-player (first player-list)]
        (show-board board)
        (if (game-over? board)
        	(show-game-conclusion board)
          (recur
            (fill-space board (move current-player board) (:marker current-player))
            (switch-player player-list current-player)))))

(defn end-game []
	(show-message "Bye!")
	(shutdown-agents))

(defn show-game-menu []
	(show-message (print-border))
	(str (map #(show-message (str (+ 1 (.indexOf game-types %)) " - " (first %) " vs " (second %))) game-types))
	(show-message "0 - Quit")
	(show-message (print-border)))

(defn play []
	(show-message "Welcome to Tic-Tac-Toe!")
	(show-game-menu)
	(show-message "Select a game: ")
	(let [type (get-input (range (+ 1 (count game-types))))]
		(if (> type 0)
			(start (player-list type) (make-board default-board-size))
			(end-game))))