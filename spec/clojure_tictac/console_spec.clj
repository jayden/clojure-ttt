(ns clojure_tictac.console-spec
  (:require [speclj.core :refer :all]
            [clojure_tictac.core :refer :all]
            [clojure_tictac.console :refer :all]))

(describe "console ui"
	(with empty-board [nil nil nil nil nil nil nil nil nil])
	(with test-board ["x" "o" "x" "o" "x" "o" "x" "o" "x"])

	(it "gets input from console"
		(should= "hello world" (with-in-str "hello world" (get-move))))
	(it "prints to console with newline"
		(should= "hey\n" (with-out-str (show-message "hey"))))
	(it "asks for and gets a message from console"
		(should= "hi computer" (with-in-str "hi computer" (prompt "hi human"))))
	(it "changes nil to blank space"
		(should= (list " " " " " " " " " " " " " " " " " ") (replace-nil @empty-board)))
	(it "formats a row to print out"
		(should= "x | o | x\n" (format-row (first (partition 3 @test-board)))))
	(it "displays the board"
		(should= "x | o | x\no | x | o\nx | o | x\n" (show-board @test-board))
		(should= "  |   |  \n  |   |  \n  |   |  \n" (show-board @empty-board)))
)