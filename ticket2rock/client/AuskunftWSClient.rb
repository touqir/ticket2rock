require 'soap/rpc/driver'

server = 'http://localhost:8080/ticket2rock/AuskunftBean'
namespace = 'http://session.ticket2rock.ejb3buch.de/jaws'
ws = SOAP::RPC::Driver.new(server, namespace)
#ws.wiredump_dev = STDOUT
ws.add_method('sucheKonzerteWeb', 'String_1', 'Date_1', 'Date_2')
result = ws.sucheKonzerteWeb(nil , nil , nil)
print result