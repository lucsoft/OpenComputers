package li.cli.oc.networking

import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Source
import org.graalvm.polyglot.io.ByteSequence


class ExecutionNode {

    fun startNode() {

        val lua = javaClass.getResource("/assets/opencomputers/wasm/lua.wasm").readBytes()
        print(lua.size)
        try {

        val contextBuilder = Context.newBuilder("wasm")

        val sourceBuilder: Source.Builder = Source.newBuilder("wasm", ByteSequence.create(lua), "test")
        val source: Source = sourceBuilder.build()
        val context: Context = contextBuilder.build()

        val data = context.eval(source)
        print(data)
        val mainFunction = context.getBindings("wasm").getMember("run_lua")
        val result = mainFunction.execute("print(_VERSION)")

        } catch(e: Exception) {
            println(e)
        }

    }
}