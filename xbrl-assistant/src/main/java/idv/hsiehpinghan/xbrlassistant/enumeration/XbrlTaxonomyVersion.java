package idv.hsiehpinghan.xbrlassistant.enumeration;

public enum XbrlTaxonomyVersion {
	TIFRS_BASI_CR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2013-03-31.xsd";
		}
	},
	TIFRS_BASI_IR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2013-03-31.xsd";
		}
	},
	TIFRS_BD_CR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2013-03-31.xsd";
		}
	},
	TIFRS_BD_ER_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2013-03-31.xsd";
		}
	},
	TIFRS_BD_IR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2013-03-31.xsd";
		}
	},
	TIFRS_CI_CR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd";
		}
	},
	TIFRS_CI_IR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2013-03-31.xsd";
		}
	},
	TIFRS_FH_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/FH/tifrs-fh-2013-03-31.xsd";
		}
	},
	TIFRS_INS_CR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2013-03-31.xsd";
		}
	},
	TIFRS_INS_IR_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2013-03-31.xsd";
		}
	},
	TIFRS_MIM_2013_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20130331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2013-03-31.xsd";
		}
	},
	TIFRS_BASI_CR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2014-03-31.xsd";
		}
	},
	TIFRS_BASI_IR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2014-03-31.xsd";
		}
	},
	TIFRS_BD_CR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2014-03-31.xsd";
		}
	},
	TIFRS_BD_ER_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2014-03-31.xsd";
		}
	},
	TIFRS_BD_IR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2014-03-31.xsd";
		}
	},
	TIFRS_CI_CR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2014-03-31.xsd";
		}
	},
	TIFRS_CI_IR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2014-03-31.xsd";
		}
	},
	TIFRS_FH_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/FH/tifrs-fh-2014-03-31.xsd";
		}
	},
	TIFRS_INS_CR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2014-03-31.xsd";
		}
	},
	TIFRS_INS_IR_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2014-03-31.xsd";
		}
	},
	TIFRS_MIM_2014_03_31 {
		@Override
		public String getPath() {
			return "/tifrs-20140331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2014-03-31.xsd";
		}
	};

	public abstract String getPath();

}
