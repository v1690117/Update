/* AdminXMLExportParser.java */
/* Generated By:JavaCC: Do not edit this line. AdminXMLExportParser.java */
package org.mxupdate.update.zparser;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import org.mxupdate.update.util.AbstractParser_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO.ParseException;
import org.mxupdate.update.util.AbstractParser_mxJPO.SimpleCharStream;
import org.mxupdate.update.util.AbstractParser_mxJPO.Token;
import org.mxupdate.update.util.AbstractParser_mxJPO.TokenMgrError;
import org.mxupdate.update.util.ParameterCache_mxJPO;

/**
 * Parses the XML export for all kind of MX administration objects.
 */
@SuppressWarnings("unused")
public class AdminXMLExportParser_mxJPO
    extends AbstractParser_mxJPO implements AdminXMLExportParserConstants_mxJPO {
    /** Stack of the parsed URL. */
    private final Stack<String> stack = new Stack<String>();
    /** Parameter cache used for the interface of the event method. */
    private ParameterCache_mxJPO paramCache;
    /** Parsed object where the events are called. */
    private AdminXMLExportObject_mxJPO object;

    /**
     * Executes a single admin XML export event.
     *
     * @param _content  content for current URL
     */
    private void event(final String _content)
    {
        final StringBuilder ret = new StringBuilder();
        for (final String tag : this.stack.subList(1, this.stack.size()))  {
            ret.append('/').append(tag);
        }

        this.object.parseAdminXMLExportEvent(this.paramCache, ret.toString(), _content);
    }

    /**
     * @param _paramCache       parameter cache
     * @param _object           object where to execute the events
     */
    public void parse(final ParameterCache_mxJPO _paramCache,
                      final AdminXMLExportObject_mxJPO _object)
        throws ParseException
    {
      this.object = _object;
      this.paramCache = _paramCache;
      this.rootParse();
    }

  final private void rootParse() throws ParseException {
    jj_consume_token(STARTTAG_START);
    jj_consume_token(STARTTAG_SINGLE);
    jj_consume_token(STARTTAG_END);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case STARTTAG_START:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      parse();
    }
    jj_consume_token(ENDTAG_START);
    jj_consume_token(ENDTAG_SINGLE);
    jj_consume_token(ENDTAG_END);
  }

  final private void parse() throws ParseException {Token tmp;
    StringBuilder text = new StringBuilder();
    jj_consume_token(STARTTAG_START);
    tmp = jj_consume_token(STARTTAG_SINGLE);
this.stack.push(tmp.image);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case STARTTAG_COMPLETE_END:{
      jj_consume_token(STARTTAG_COMPLETE_END);
this.event(null);this.stack.pop();
      break;
      }
    case STARTTAG_END:{
      jj_consume_token(STARTTAG_END);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case TEXT:
        case DTDINFO:
        case CDATA_START:{
          ;
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          break label_2;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DTDINFO:{
          tmp = jj_consume_token(DTDINFO);
text.append(tmp.image);
          break;
          }
        case TEXT:{
          tmp = jj_consume_token(TEXT);
text.append(tmp.image);
          break;
          }
        case CDATA_START:{
          jj_consume_token(CDATA_START);
          label_3:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case CDATA_CHAR:{
              ;
              break;
              }
            default:
              jj_la1[2] = jj_gen;
              break label_3;
            }
            tmp = jj_consume_token(CDATA_CHAR);
text.append(tmp.image);
          }
          jj_consume_token(CDATA_END);
          break;
          }
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
this.event(text.toString());
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case STARTTAG_START:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_4;
        }
        parse();
      }
      jj_consume_token(ENDTAG_START);
      tmp = jj_consume_token(ENDTAG_SINGLE);
this.stack.pop();
      jj_consume_token(ENDTAG_END);
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  /** Generated Token Manager. */
  public AdminXMLExportParserTokenManager_mxJPO token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[6];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80,0xe00,0x80000,0xe00,0x80,0x18000,};
   }

  /** Constructor with InputStream. */
  public AdminXMLExportParser_mxJPO(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AdminXMLExportParser_mxJPO(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AdminXMLExportParserTokenManager_mxJPO(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AdminXMLExportParser_mxJPO(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AdminXMLExportParserTokenManager_mxJPO(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new SimpleCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new AdminXMLExportParserTokenManager_mxJPO(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AdminXMLExportParser_mxJPO(AdminXMLExportParserTokenManager_mxJPO tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AdminXMLExportParserTokenManager_mxJPO tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[21];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 6; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 21; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
