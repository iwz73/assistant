package idv.hsiehpinghan.xbrlassistant.xbrl;

public class Presentation {
	public static class Id {
		public static String AccountantsReport = "http://www.xbrl.org/tifrs/ar/role/AccountantsReport";
		public static String BalanceSheet = "http://www.xbrl.org/tifrs/fr/role/BalanceSheet";
		public static String StatementOfComprehensiveIncome = "http://www.xbrl.org/tifrs/fr/role/StatementOfComprehensiveIncome";
		public static String StatementOfChangesInEquity = "http://www.xbrl.org/tifrs/fr/role/StatementOfChangesInEquity";
		public static String StatementOfCashFlows = "http://www.xbrl.org/tifrs/fr/role/StatementOfCashFlows";
		public static String FinancialStatementsNotes = "http://www.xbrl.org/tifrs/notes/role/FinancialStatementsNotes";
		public static String HistoryAndOrganization = "http://www.xbrl.org/tifrs/notes/role/HistoryAndOrganization";
		public static String DateAndProceduresOfAuthorisationForIssueOfFinancialStatements = "http://www.xbrl.org/tifrs/notes/role/DateAndProceduresOfAuthorisationForIssueOfFinancialStatements";
		public static String ApplicationOfNewlyIssuedOrAmendedStandardsAndInterpretations = "http://www.xbrl.org/tifrs/notes/role/ApplicationOfNewlyIssuedOrAmendedStandardsAndInterpretations";
		public static String SummaryOfSignificantAccountingPolicies = "http://www.xbrl.org/tifrs/notes/role/SummaryOfSignificantAccountingPolicies";
		public static String SourcesOfUncertaintyFromSignificantAccountingJudgmentsAssumptionsAndEstimations = "http://www.xbrl.org/tifrs/notes/role/SourcesOfUncertaintyFromSignificantAccountingJudgmentsAssumptionsAndEstimations";
		public static String DisclosureOfGeneralInformationAboutFinancialStatements = "http://www.xbrl.org/tifrs/role/DisclosureOfGeneralInformationAboutFinancialStatements";
		public static String LoansToOthers = "http://www.xbrl.org/tifrs/notes/role/LoansToOthers";
		public static String EndorsementGuaranteeProvidedToOthers = "http://www.xbrl.org/tifrs/notes/role/EndorsementGuaranteeProvidedToOthers";
		public static String NamesLocationsAndRelatedInformationOfInvesteesOverWhichTheCompanyExercisesSignificantInfluence = "http://www.xbrl.org/tifrs/notes/role/NamesLocationsAndRelatedInformationOfInvesteesOverWhichTheCompanyExercisesSignificantInfluence";
		public static String InvestmentInMainlandChina = "http://www.xbrl.org/tifrs/notes/role/InvestmentInMainlandChina";
	}
	
	public static class Attribute {
		public static final String ORDER = "order";
		public static final String CHINESE_LABEL = "chinese_label";
		public static final String ENGLISH_LABEL = "english_label";
		public static final String VALUES = "values";
		public static final String VALUE = "value";
		public static final String UNIT = "unit";
		public static final String PERIOD_TYPE = "periodType";
		public static final String INSTANT = "instant";
		public static final String START_DATE = "startDate";
		public static final String END_DATE = "endDate";
	}
	
	public static class Unit {
		public static final String TWD = "TWD";
		public static final String SHARES = "Shares";
	}
}
