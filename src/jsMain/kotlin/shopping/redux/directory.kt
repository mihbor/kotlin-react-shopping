package shopping.redux

import react.redux.useSelector
import redux.RAction
import shopping.model.Collection
import shopping.model.DirectoryState
import shopping.model.Section
import shopping.model.State

data class PopulateSections(val sections: List<Section>): RAction
data class PopulateCollections(val collections: List<Collection>): RAction

fun directoryHandler(state: DirectoryState = DirectoryState(), action: RAction) = when(action) {
  is PopulateSections -> DirectoryState(action.sections, state.collections)
  is PopulateCollections -> DirectoryState(state.sections, action.collections.map{ it.routeName to it }.toMap())
  else -> state
}

fun getSections() = useSelector<State, List<Section>> { it.directory.sections }

fun getCollections() = useSelector<State, Map<String, Collection>> { it.directory.collections }

fun getCollection(name: String) = useSelector<State, Collection?> { it.directory.collections[name] }