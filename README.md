This was the first time I touched NIO like this: a bit of stackoverflow, some utube videos and some documentation
(of course some tries and failures). As such, treat this in the proper manner. Fun activity nevertheless.


The idea is kind of trivial:

 - client generates command (as String)
 - I parse and validate those to obtain `CacheCommandWithArguments` via `CommandParser::parse`.
 - serialize the above with plain java serialization, transport them to the server (ByteBuffers and stuff)
 - deserialize command on the server (no validation is done, we know stuff is correct already)
   use a CHM as backing cache (I see no need for something else, though I could be "cool" and write a few spin locks, VarHandles, etc; but why?)
 - execute the commands on the server and return some meaningful result
 

 What I could improve given some time to sleep over this:

 - probably read more about NIO, as I vaguely understood some points. Seems like I'm not the only one, may be source code of Netty a bit would answer some questions.
 - may be re-strucre code a bit in a more easy to read way, clearly separate parsing fron NIO, commands, etc. For a POC-like, its fine, imho.
 - tests. I can't figure out (yet) how to properly write tests against this sockets: in a way that would make me entirely happy.
   I don't want to do stupid things here, so I left these out. I should look in the JDK sources itself (but that takes quite some time)
 - measure the performance against multiple clients, I only played with a few, locally. More threads? Do I really need NIO, or blocking + virtual threads would be the way to go?
