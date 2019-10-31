package Integracion.Querys;

public class QueryFactoryImp extends QueryFactory {
	
	@Override
	public Query newQuery(Integer queryEvent) {
		switch(queryEvent) {
		case QueryEvents.GET_INFO_EVENT:
			return new GetInfoFromTicket();
		}
		return null;
	}

}
