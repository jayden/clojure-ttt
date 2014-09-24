(ns clojure_tictac.ttt-rules
	(:require [clojure_tictac.board :refer :all]))

(defn get-winning-combos [board]
	(concat (get-rows board) (get-cols board) (get-diags board)))

(defn distinct-board [board]
	(map #(distinct %) (get-winning-combos board)))

(defn winner? [board]
	(not (nil? (some #(= 1 (count %)) (distinct-board board)))))

(defn game-winner [board]
	(let [mark  (filter #(= 1 (count %)) (distinct-board board))]
		(if (empty? mark)
			nil
			(nth (flatten mark) 0))))

(defn draw? [board]
	(if (winner? board)
		false
		(empty? (filter #(nil? (get board %)) (range (count board))))))

(defn game-over? [board]
	(or (winner? board) (draw? board)))

(defn available-moves [board]
	(if (draw? board)
		[]
		(empty-spaces (board-indices board))))

(defn move-within-space? [board move]
	(and (< move (count board)) (>= move 0)))

(defn empty-space? [board move]
	(not (get board move)))

(defn valid-move? [board move]
	(and (move-within-space? board move) (empty-space? board move)))