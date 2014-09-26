(ns clojure_tictac.console-spec
  (:require [speclj.core :refer :all]
            [clojure_tictac.core :refer :all]
            [clojure_tictac.console :refer :all]))

(describe "console ui"
	(with empty-board [nil nil nil nil nil nil nil nil nil])
	(with test-board ["x" "o" "x" "o" "x" "o" "x" "o" "x"])

	(it "prints to console with newline"
		(should= "hey\n" (with-out-str (show-message "hey"))))

	(it "gets input"
		(should= 8 (with-in-str "8" (get-input (range 9))))
		(should-throw 
						(with-out-str (with-in-str "10" (get-input (range 9))))))

	(it "changes nil to blank space"
		(should= (list " " " " " " " " " " " " " " " " " ")
						(replace-nil @empty-board)))
	
	(it "formats a row to print out"
		(should= "x | o | x\n"
						(format-row (first (partition 3 @test-board)))))
	
	(it "formats the board as a string"
		(should= "x | o | x\no | x | o\nx | o | x\n"
						(board-string @test-board))
		(should= "  |   |  \n  |   |  \n  |   |  \n" 
						(board-string @empty-board)))
)