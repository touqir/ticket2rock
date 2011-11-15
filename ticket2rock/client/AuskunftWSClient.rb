require 'soap/rpc/driver'

endpointUrl = 'http://localhost:8080/ticket2rock/KonzertInfo/AuskunftBean' 
namespace = 'http://session.ticket2rock.ejb3buch.de/'
ws = SOAP::RPC::Driver.new(endpointUrl ,namespace)
#ws.wiredump_dev = STDOUT
ws.add_method('sucheKonzerteWeb', 'Ortsname', 'Startdatum', 'Enddatum')
result = ws.sucheKonzerteWeb(nil, nil , nil)
print result
