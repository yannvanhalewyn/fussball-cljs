(ns fussball.flux)

(defmacro defaction [name args body]
  (list 'defmethod 'fussball.dispatcher/action name ['_ args] body))
