# map-helpers

A small Clojure library providing functions for manipulating maps

## Usage

Examples:
	(require '[map-helpers.core :as helpers])

	; create a "transitive map":
	(helpers/transitive-map {:Language :Functional} {:Functional :Lisp} {:Lisp :Clojure}) => {:Language :Clojure}

	; merge maps using different functions for every key 
	(helpers/merge-with-keys {:add + :mul *}
				 {:add 2 :mul 3}
				 {:add 5 :mul 10}) => {:add 7 :mul 30}

	; filter map according to keys:
	(helpers/filter-keys {0 "foo" 1 "bar" 2 "baz"} even?) => {0 "foo" 2 "baz"}
## License

Copyright © 2014

Distributed under the Eclipse Public License, the same as Clojure.
