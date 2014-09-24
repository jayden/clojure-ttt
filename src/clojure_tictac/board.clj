(ns clojure_tictac.board)

(defn make-board [n]
	(vec (repeat n nil)))

(defn fill-space [board space mark]
	(assoc board space mark))

(defn board-size [board]
	(int (java.lang.Math/sqrt (count board))))

(defn get-rows [board]
	(let [size (board-size board)]
		(partition size board)))

(defn get-cols [board]
	(apply map vector (get-rows board)))

(defn left-diag [board]
	(let [step (+ (board-size board) 1)
				indices (range 0 (count board) step)]
		(map #(nth board %) indices)))

(defn right-diag [board]
	(let [step (- (board-size board) 1)
				indices (range step (dec (count board)) step)]
		(map #(nth board %) indices)))

(defn get-diags [board]
	(list (left-diag board) (right-diag board)))

(defn full? [board]
	(not (some nil? board)))

(defn board-indices [board]
	(map-indexed (fn [index item] (if item item index)) board))

(defn empty-spaces [board]
	(filter number? (board-indices board)))
