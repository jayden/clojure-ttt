(ns clojure_tictac.players
	(:require [clojure_tictac.ttt-rules :refer :all]		
						[clojure_tictac.board :refer :all]
						[clojure_tictac.console :refer :all]))

(declare score-move)

(def win 1)
(def lose -1)
(def tie 0)

(defn opponent [marker]
	(if (= marker x)
		o
		x))

(defn best-move [board player]
  (let [moves (empty-spaces board)
        scores (zipmap moves (map #(score-move (fill-space board % player) player win) moves))
        best-score (reduce max (vals scores))
        best-moves (filter #(= (scores %) best-score) moves)]
    (first best-moves)))

(defn score-move
  [board player win-score]
  (cond
   (= (game-winner board) player) win-score
   (= (game-winner board) (opponent player)) (* lose win-score)
   (empty? (empty-spaces board)) tie
   :else (let [opponent (opponent player)
               next-board (fill-space board (best-move board opponent) opponent)]
           (recur next-board opponent (* lose win-score)))))

(def score-move (memoize score-move))

(defprotocol Player
	(marker [this])
	(move [this board]))

(defrecord Human [marker]
	Player
	(marker [this] (:marker this))
	(move [this board]
		(show-message "Select a move: ")
		(get-input (empty-spaces board))))

(defrecord Computer [marker]
	Player
	(marker [this] (:marker this))
	(move [this board]
		(best-move board (:marker this))))

(defn create-player [player marker]
	(case player
		"Human" (clojure_tictac.players.Human. marker)
		"Computer" (clojure_tictac.players.Computer. marker)))