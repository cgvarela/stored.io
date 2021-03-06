package io.stored.server.sql


import net.sf.jsqlparser.expression._
import net.sf.jsqlparser.statement.select.SubSelect
import net.sf.jsqlparser.schema.Column
import operators.arithmetic._
import operators.conditional.{AndExpression, OrExpression}
import operators.relational._
import io.stored.server.common.ProjectionField


class ExpressionValueExtractor extends ExpressionVisitor {
  var expressionValue: BigInt = null

  def visit(p1: NullValue) {}
  def visit(p1: Function) {}
  def visit(p1: InverseExpression) {}
  def visit(p1: JdbcParameter) {}
  def visit(p1: DoubleValue) = expressionValue = ProjectionField.md5Hash(p1.getValue)
  def visit(p1: LongValue) = expressionValue = ProjectionField.md5Hash(p1.getValue)
  def visit(p1: StringValue) = expressionValue = ProjectionField.md5Hash(p1.getValue)
  def visit(p1: DateValue) {}
  def visit(p1: TimeValue) {}
  def visit(p1: TimestampValue) {}
  def visit(p1: Parenthesis) {}
  def visit(p1: Addition) {}
  def visit(p1: Division) {}
  def visit(p1: Multiplication) {}
  def visit(p1: Subtraction) {}
  def visit(p1: AndExpression) {}
  def visit(p1: OrExpression) {}
  def visit(p1: Between) {}
  def visit(p1: EqualsTo) {}
  def visit(p1: GreaterThan) {}
  def visit(p1: GreaterThanEquals) {}
  def visit(p1: InExpression) {}
  def visit(p1: IsNullExpression) {}
  def visit(p1: LikeExpression) {}
  def visit(p1: MinorThan) {}
  def visit(p1: MinorThanEquals) {}
  def visit(p1: NotEqualsTo) {}
  def visit(p1: Column) {}
  def visit(p1: SubSelect) {}
  def visit(p1: CaseExpression) {}
  def visit(p1: WhenClause) {}
  def visit(p1: ExistsExpression) {}
  def visit(p1: AllComparisonExpression) {}
  def visit(p1: AnyComparisonExpression) {}
  def visit(p1: Concat) {}
  def visit(p1: Matches) {}
  def visit(p1: BitwiseAnd) {}
  def visit(p1: BitwiseOr) {}
  def visit(p1: BitwiseXor) {}
}
