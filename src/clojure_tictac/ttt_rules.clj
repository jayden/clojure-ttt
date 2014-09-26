(ns clojure_tictac.ttt-rules
	(:require [clojure_tictac.board :refer :all]))

(def x "x")
(def o "o")

(defn get-winning-combos [board]
	(concat (get-rows board) (get-cols board) (get-diags board)))

(defn- winner-in-set [board spaces]
  (let [player (first spaces)]
    (if (every? #(= player %) spaces)
    	player
    	nil)))

(defn game-winner [board]
  (some #(winner-in-set board %) (get-winning-combos board)))

(defn winner? [board]
	(not (nil? (game-winner board))))

(defn game-over? [board]
  (or (winner? board)
    (full? board)))

(defn available-moves [board]
	(if (full? board)
		[]
		(empty-spaces (board-indices board))))

(defn empty-space? [board move]
	(not (get board move)))
