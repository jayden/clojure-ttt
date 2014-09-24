(ns clojure_tictac.console)

(defn get-move [] (read-line))

(defn show-message [message] (println message))

(defn prompt [message]
	(show-message message)
	(get-move))

(defn replace-nil [row]
	(map (fn [space] (if (nil? space) " " space)) row))

(defn format-row [row]
	(str (clojure.string/join " | " (replace-nil row)) "\n"))

(defn show-board [board]
	(loop [rows board board-string ""]
		(if (= 0 (count rows))
			board-string
			(let [row (first rows)
				updated-board-string (str board-string (format-row row))]
				(recur (rest rows) updated-board-string)))))