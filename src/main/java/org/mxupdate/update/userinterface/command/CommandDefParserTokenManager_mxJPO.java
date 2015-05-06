/* Generated By:JavaCC: Do not edit this line. CommandDefParserTokenManager.java */
package org.mxupdate.update.userinterface.command;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.mxupdate.mapping.TypeDef_mxJPO;
import org.mxupdate.update.userinterface.Command_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO;
import org.mxupdate.update.util.AdminPropertyList_mxJPO.AdminProperty;
import org.mxupdate.update.util.ParameterCache_mxJPO;

/** Token Manager. */
public class CommandDefParserTokenManager_mxJPO implements CommandDefParserConstants_mxJPO
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x100L) != 0L)
            return 14;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 104:
         return jjMoveStringLiteralDfa1_0(0x240L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      default :
         return jjMoveNfa_0(11, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x8400L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x40L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x100L);
      case 111:
         if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(1, 14);
         break;
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x2200L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x40L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      case 116:
         if ((active0 & 0x100L) != 0L)
            return jjStopAtPos(2, 8);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x20L);
      case 100:
         return jjMoveStringLiteralDfa4_0(active0, 0xc0L);
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x400L);
      case 102:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(3, 9);
         break;
      case 112:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa5_0(active0, 0x80L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(4, 15);
         return jjMoveStringLiteralDfa5_0(active0, 0x2040L);
      case 108:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(4, 10);
         break;
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa6_0(active0, 0x80L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x20L);
      case 110:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(5, 6);
         break;
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(6, 7);
         break;
      case 112:
         return jjMoveStringLiteralDfa7_0(active0, 0x20L);
      case 116:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x20L);
      case 121:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(7, 13);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x20L) != 0L)
            return jjStopAtPos(10, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 18;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 13:
                  if ((0x100002600L & l) != 0L)
                     jjAddStates(0, 1);
                  break;
               case 16:
                  if ((0x100002600L & l) != 0L)
                     jjAddStates(2, 3);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 14:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 15;
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 11:
                  if (curChar == 97)
                     jjAddStates(4, 5);
                  else if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 9;
                  else if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if (curChar == 114)
                     kind = 11;
                  break;
               case 1:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 2:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 4:
                  if (curChar == 103)
                     kind = 12;
                  break;
               case 5:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 12:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 17:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 18 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_3(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_3(int pos, long active0)
{
   return jjMoveNfa_3(jjStopStringLiteralDfa_3(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_3()
{
   switch(curChar)
   {
      default :
         return jjMoveNfa_3(0, 0);
   }
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_3(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 8;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffffa00000000L & l) != 0L)
                  {
                     if (kind > 37)
                        kind = 37;
                     jjCheckNAdd(7);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(6, 8);
                  break;
               case 1:
                  if ((0xfffffffb00000000L & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 2:
                  if (curChar == 34 && kind > 36)
                     kind = 36;
                  break;
               case 4:
                  if (curChar == 34)
                     jjCheckNAddStates(6, 8);
                  break;
               case 7:
                  if ((0xfffffffa00000000L & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(7);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 7:
                  if ((0xd7ffffffffffffffL & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(7);
                  break;
               case 1:
                  if ((0xd7ffffffffffffffL & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 3:
                  if (curChar == 92)
                     jjAddStates(9, 11);
                  break;
               case 5:
                  if (curChar == 123)
                     jjCheckNAddStates(6, 8);
                  break;
               case 6:
                  if (curChar == 125)
                     jjCheckNAddStates(6, 8);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 7:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(7);
                  break;
               case 1:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(6, 8);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 8 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_2(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_2(int pos, long active0)
{
   return jjMoveNfa_2(jjStopStringLiteralDfa_2(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      default :
         return jjMoveNfa_2(0, 0);
   }
}
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 8;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffffa00000000L & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                     jjCheckNAdd(7);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(6, 8);
                  break;
               case 1:
                  if ((0xfffffffb00000000L & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 2:
                  if (curChar == 34 && kind > 28)
                     kind = 28;
                  break;
               case 4:
                  if (curChar == 34)
                     jjCheckNAddStates(6, 8);
                  break;
               case 7:
                  if ((0xfffffffa00000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAdd(7);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 7:
                  if ((0xd7ffffffffffffffL & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAdd(7);
                  break;
               case 1:
                  if ((0xd7ffffffffffffffL & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 3:
                  if (curChar == 92)
                     jjAddStates(9, 11);
                  break;
               case 5:
                  if (curChar == 123)
                     jjCheckNAddStates(6, 8);
                  break;
               case 6:
                  if (curChar == 125)
                     jjCheckNAddStates(6, 8);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 7:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAdd(7);
                  break;
               case 1:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(6, 8);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 8 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_1(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_1(int pos, long active0)
{
   return jjMoveNfa_1(jjStopStringLiteralDfa_1(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      default :
         return jjMoveNfa_1(0, 0);
   }
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 8;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffffa00000000L & l) != 0L)
                  {
                     if (kind > 21)
                        kind = 21;
                     jjCheckNAdd(7);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(6, 8);
                  break;
               case 1:
                  if ((0xfffffffb00000000L & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 2:
                  if (curChar == 34 && kind > 20)
                     kind = 20;
                  break;
               case 4:
                  if (curChar == 34)
                     jjCheckNAddStates(6, 8);
                  break;
               case 7:
                  if ((0xfffffffa00000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(7);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 7:
                  if ((0xd7ffffffffffffffL & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(7);
                  break;
               case 1:
                  if ((0xd7ffffffffffffffL & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 3:
                  if (curChar == 92)
                     jjAddStates(9, 11);
                  break;
               case 5:
                  if (curChar == 123)
                     jjCheckNAddStates(6, 8);
                  break;
               case 6:
                  if (curChar == 125)
                     jjCheckNAddStates(6, 8);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 7:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(7);
                  break;
               case 1:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(6, 8);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 8 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   13, 3, 16, 10, 14, 17, 1, 2, 3, 4, 5, 6, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, "\144\145\163\143\162\151\160\164\151\157\156", 
"\150\151\144\144\145\156", "\41\150\151\144\144\145\156", "\141\154\164", "\150\162\145\146", 
"\154\141\142\145\154", null, null, "\160\162\157\160\145\162\164\171", "\164\157", 
"\166\141\154\165\145", null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "STRING_EXPECTED",
   "SETTING_EXPECTED",
   "ADMINREF_EXPECTED",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 2, 1, 3, 1, -1, -1, -1, -1, 0, 0, -1, -1, -1, 
   -1, -1, -1, 1, 0, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 
};
static final long[] jjtoToken = {
   0x303030ffe1L, 
};
static final long[] jjtoSkip = {
   0xf0f0f001eL, 
};
protected SimpleCharStream_mxJPO input_stream;
private final int[] jjrounds = new int[18];
private final int[] jjstateSet = new int[36];
protected char curChar;
/** Constructor. */
public CommandDefParserTokenManager_mxJPO(SimpleCharStream_mxJPO stream){
   if (SimpleCharStream_mxJPO.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public CommandDefParserTokenManager_mxJPO(SimpleCharStream_mxJPO stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream_mxJPO stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 18; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream_mxJPO stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 4 || lexState < 0)
      throw new TokenMgrError_mxJPO("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError_mxJPO.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token_mxJPO jjFillToken()
{
   final Token_mxJPO t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token_mxJPO.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token_mxJPO getNextToken() 
{
  Token_mxJPO matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       break;
     case 2:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       break;
     case 3:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_3();
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError_mxJPO(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError_mxJPO.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
