zna(john, java).
zna(john, git).
zna(john, scala).
zna(przemek, java).
zna(przemek, scala).
zna(ziemowit, git).
zna(ziemowit, java).


programista(X) :-
  zna(X, java),
  zna(X, scala).

% programista(przemek).
% programista(ziemowit).
% programista(X), write(X), nl, fail.

% 1 + 2 + 3 + 4 + 5 + ...
suma(1,X) :-
  X is 1.

suma(X,Y) :-
  I is X - 1,
  suma(I, J),
  Y is X + J.

% Hanoi

move(1,X,Y,_) :-  
  write('Move top disk from '), 
  write(X), 
  write(' to '), 
  write(Y), 
  nl. 
move(N,X,Y,Z) :- 
  N>1, 
  M is N-1, 
  move(M,X,Z,Y), 
  move(1,X,Y,_), 
  move(M,Z,Y,X).  
