package Integracion.Querys;

public class QueryFactoryImp extends QueryFactory {
	
	@Override
	public Query newQuery(Integer queryEvent) {
		switch(queryEvent) {
		case QueryEvents.GET_INFO_EVENT:
			return new GetInfoFromTicket();
		case QueryEvents.GET_PRODUCT_COUNT:
			return new ProductCount();
		case QueryEvents.GET_BEST_PROVIDER:
			return new BestProvider();
		case QueryEvents.GET_BEST_PRODUCT:
			return new GetBestProductSelled();
		}
		return null;
	}

}
