(ns clojure_tictac.console
	(:require [clojure_tictac.board :refer [board-size]]))

(defn show-message [message] (println message))

(defn get-input [limit]
  (flush)
  (let [input (try (read-string (read-line)) (catch Exception e))]
    (if (.contains limit input)
      input
      (do
        (show-message "invalid selection! try again:")
        (recur limit)))))

(defn replace-nil [row]
	(map (fn [space] (if (nil? space) " " space)) row))

(defn format-row [row]
	(str (clojure.string/join " | " (replace-nil row)) "\n"))

(defn board-string [board]
	(loop [rows (partition (board-size board) board) board-string ""]
		(if (= 0 (count rows))
			board-string
			(let [row (first rows)
				updated-board-string (str board-string (format-row row))]
				(recur (rest rows) updated-board-string)))))

(defn show-board [board]
	(show-message (board-string board)))

(defn borderline []
	"-------------------------")

(defn declare-winner [game-winner]
	(show-message (str game-winner " wins!")))

(defn declare-draw []
	(show-message "It's a draw!"))

(defn welcome-message []
	(show-message "Welcome to Tic-Tac-Toe!"))

(defn select-game-message []
	(show-message "Select a game: "))

(defn select-move-message []
	(show-message "Select a move: "))